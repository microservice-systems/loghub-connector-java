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

/**
 * @author Dmitry Kotlyarov
 * @since 1.0
 */
public final class ConfigDefaults {
    public static String central = "loghub.net";
    public static String organization = null;
    public static String environment = null;
    public static String registry = null;
    public static String group = null;
    public static String application = null;
    public static String version = null;
    public static String revision = null;
    public static String name = null;
    public static String description = null;
    public static String repository = null;
    public static RepositoryType repositoryType = null;

    private ConfigDefaults() {
    }
}
