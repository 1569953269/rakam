/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.rakam.aws;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.rakam.plugin.CollectionStreamQuery;

import java.util.List;

/**
 * Created by buremba <Burak Emre Kabakcı> on 17/09/15 02:31.
 */
public class StreamQuery {
    public final String id;
    public final String memberId;
    public final String project;
    public final List<CollectionStreamQuery> collections;

    @JsonCreator
    public StreamQuery(@JsonProperty("id") String id,
                       @JsonProperty("memberId") String memberId,
                       @JsonProperty("project") String project,
                       @JsonProperty("collections") List<CollectionStreamQuery> collections) {
        this.id = id;
        this.memberId = memberId;
        this.project = project;
        this.collections = collections;
    }
}
