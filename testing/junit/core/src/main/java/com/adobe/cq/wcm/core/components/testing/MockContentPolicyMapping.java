/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 ~ Copyright 2017 Adobe Systems Incorporated
 ~
 ~ Licensed under the Apache License, Version 2.0 (the "License");
 ~ you may not use this file except in compliance with the License.
 ~ You may obtain a copy of the License at
 ~
 ~     http://www.apache.org/licenses/LICENSE-2.0
 ~
 ~ Unless required by applicable law or agreed to in writing, software
 ~ distributed under the License is distributed on an "AS IS" BASIS,
 ~ WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 ~ See the License for the specific language governing permissions and
 ~ limitations under the License.
 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
package com.adobe.cq.wcm.core.components.testing;

import java.util.Calendar;

import org.apache.commons.lang.StringUtils;
import org.apache.sling.api.adapter.SlingAdaptable;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;

import com.day.cq.wcm.api.Template;
import com.day.cq.wcm.api.policies.ContentPolicy;
import com.day.cq.wcm.api.policies.ContentPolicyMapping;

public class MockContentPolicyMapping extends SlingAdaptable implements ContentPolicyMapping {

    Resource contentPolicyResource;

    public MockContentPolicyMapping(Resource contentPolicyResource) {
        this.contentPolicyResource = contentPolicyResource;
    }

    @Override
    public String getPath() {
        return null;
    }

    @Override
    public Calendar getLastModified() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public ContentPolicy getPolicy() {
        ValueMap valueMap = contentPolicyResource.adaptTo(ValueMap.class);
        if (valueMap.containsKey("cq:policy")) {
            String policyPath = valueMap.get("cq:policy", StringUtils.EMPTY);
            if (StringUtils.isNotEmpty(policyPath)) {
                policyPath = "/conf/coretest/settings/wcm/policies/" + policyPath;
                Resource policyResource = contentPolicyResource.getResourceResolver().getResource(policyPath);
                if (policyResource != null) {
                    return new MockContentPolicy(policyResource);
                }
            }
        }
        return null;
    }

    @Override
    public Template getTemplate() {
        return null;
    }
}
