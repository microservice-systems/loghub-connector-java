/*
 * Copyright (C) 2020 Microservice Systems, Inc.
 * All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package systems.microservice.loghub.connector;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * @author Dmitry Kotlyarov
 * @since 1.0
 */
public final class Config {
    public static final String CENTRAL = createCentral();
    public static final String ORGANIZATION = createOrganization();

    private Config() {
    }

    private static String createCentral() {
        String c = System.getenv("LOGHUB_CENTRAL");
        if (c == null) {
            c = System.getProperty("loghub.central");
            if (c == null) {
                c = getString("/META-INF/loghub/CENTRAL");
                if (c == null) {
                    c = Defaults.central;
                }
            }
        }
        return Validation.domainNullable("CENTRAL", c);
    }

    private static String createOrganization() {
        String o = System.getenv("LOGHUB_ORGANIZATION");
        if (o == null) {
            o = System.getProperty("loghub.organization");
            if (o == null) {
                o = getString("/META-INF/loghub/ORGANIZATION");
                if (o == null) {
                    o = Defaults.organization;
                }
            }
        }
        return Validation.nameNullable("ORGANIZATION", o);
    }

    private static byte[] getArray(String name) {
        InputStream in = Config.class.getResourceAsStream(name);
        if (in != null) {
            try (InputStream in1 = in) {
                byte[] data = new byte[in1.available()];
                int read = in1.read(data);
                if (read == data.length) {
                    return data;
                } else {
                    throw new RuntimeException(String.format("Read %d bytes from resource '%s:%s' of size %d", read, Config.class.getCanonicalName(), name, data.length));
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            return null;
        }
    }

    private static String getString(String name) {
        byte[] a = getArray(name);
        if (a != null) {
            return new String(a, StandardCharsets.UTF_8);
        } else {
            return null;
        }
    }
}
