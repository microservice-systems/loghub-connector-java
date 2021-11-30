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

import systems.microservice.loghub.facade.Validator;

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
    public static final String ENVIRONMENT = createEnvironment();
    public static final String REGISTRY = createRegistry();
    public static final String GROUP = createGroup();
    public static final String APPLICATION = createApplication();
    public static final String VERSION = createVersion();
    public static final String REVISION = createRevision();
    public static final String NAME = createName();
    public static final String DESCRIPTION = createDescription();
    public static final String REPOSITORY = createRepository();

    private Config() {
    }

    private static String createCentral() {
        String c = System.getenv("CENTRAL");
        if (c == null) {
            c = System.getProperty("central");
            if (c == null) {
                c = getString("/META-INF/loghub/CENTRAL");
                if (c == null) {
                    c = ConfigDefaults.central;
                }
            }
        }
        return Validator.domainNullable("CENTRAL", c);
    }

    private static String createOrganization() {
        String o = System.getenv("ORGANIZATION");
        if (o == null) {
            o = System.getProperty("organization");
            if (o == null) {
                o = getString("/META-INF/loghub/ORGANIZATION");
                if (o == null) {
                    o = ConfigDefaults.organization;
                }
            }
        }
        return Validator.nameNullable("ORGANIZATION", o);
    }

    private static String createEnvironment() {
        String e = System.getenv("ENVIRONMENT");
        if (e == null) {
            e = System.getProperty("environment");
            if (e == null) {
                e = getString("/META-INF/loghub/ENVIRONMENT");
                if (e == null) {
                    e = ConfigDefaults.environment;
                }
            }
        }
        return Validator.nameNullable("ENVIRONMENT", e);
    }

    private static String createRegistry() {
        String r = System.getenv("REGISTRY");
        if (r == null) {
            r = System.getProperty("registry");
            if (r == null) {
                r = getString("/META-INF/loghub/REGISTRY");
                if (r == null) {
                    r = ConfigDefaults.registry;
                }
            }
        }
        return r;
    }

    private static String createGroup() {
        String g = System.getenv("GROUP");
        if (g == null) {
            g = System.getProperty("group");
            if (g == null) {
                g = getString("/META-INF/loghub/GROUP");
                if (g == null) {
                    g = ConfigDefaults.group;
                }
            }
        }
        return Validator.nameWithDotsNullable("GROUP", g);
    }

    private static String createApplication() {
        String a = System.getenv("APPLICATION");
        if (a == null) {
            a = System.getProperty("application");
            if (a == null) {
                a = getString("/META-INF/loghub/APPLICATION");
                if (a == null) {
                    a = ConfigDefaults.application;
                }
            }
        }
        return Validator.nameNullable("APPLICATION", a);
    }

    private static String createVersion() {
        String v = System.getenv("VERSION");
        if (v == null) {
            v = System.getProperty("version");
            if (v == null) {
                v = getString("/META-INF/loghub/VERSION");
                if (v == null) {
                    v = ConfigDefaults.version;
                }
            }
        }
        return Validator.versionNullable("VERSION", v);
    }

    private static String createRevision() {
        String r = System.getenv("REVISION");
        if (r == null) {
            r = System.getProperty("revision");
            if (r == null) {
                r = getString("/META-INF/loghub/REVISION");
                if (r == null) {
                    r = ConfigDefaults.revision;
                }
            }
        }
        return Validator.revisionNullable("REVISION", r);
    }

    private static String createName() {
        String n = System.getenv("NAME");
        if (n == null) {
            n = System.getProperty("name");
            if (n == null) {
                n = getString("/META-INF/loghub/NAME");
                if (n == null) {
                    n = ConfigDefaults.name;
                }
            }
        }
        return n;
    }

    private static String createDescription() {
        String d = System.getenv("DESCRIPTION");
        if (d == null) {
            d = System.getProperty("description");
            if (d == null) {
                d = getString("/META-INF/loghub/DESCRIPTION");
                if (d == null) {
                    d = ConfigDefaults.description;
                }
            }
        }
        return d;
    }

    private static String createRepository() {
        String r = System.getenv("REPOSITORY");
        if (r == null) {
            r = System.getProperty("repository");
            if (r == null) {
                r = getString("/META-INF/loghub/REPOSITORY");
                if (r == null) {
                    r = ConfigDefaults.repository;
                }
            }
        }
        return r;
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
