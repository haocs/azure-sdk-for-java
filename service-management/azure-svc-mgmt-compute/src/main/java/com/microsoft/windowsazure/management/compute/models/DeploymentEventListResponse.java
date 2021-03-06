/**
 * 
 * Copyright (c) Microsoft and contributors.  All rights reserved.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * 
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */

// Warning: This code was generated by a tool.
// 
// Changes to this file may cause incorrect behavior and will be lost if the
// code is regenerated.

package com.microsoft.windowsazure.management.compute.models;

import com.microsoft.windowsazure.core.LazyArrayList;
import com.microsoft.windowsazure.core.OperationResponse;

import java.util.ArrayList;

/**
* Identifies the list of reboot events due to planned maintenance that impacted
* a deployment in the optionally provided timeframe.
*/
public class DeploymentEventListResponse extends OperationResponse {
    private String continuationToken;
    
    /**
    * Optional. Gets or sets the deployment event's continuation token value.
    * @return The ContinuationToken value.
    */
    public String getContinuationToken() {
        return this.continuationToken;
    }
    
    /**
    * Optional. Gets or sets the deployment event's continuation token value.
    * @param continuationTokenValue The ContinuationToken value.
    */
    public void setContinuationToken(final String continuationTokenValue) {
        this.continuationToken = continuationTokenValue;
    }
    
    private ArrayList<RebootEvent> deploymentEvents;
    
    /**
    * Optional. Gets or sets the list of reboot events.
    * @return The DeploymentEvents value.
    */
    public ArrayList<RebootEvent> getDeploymentEvents() {
        return this.deploymentEvents;
    }
    
    /**
    * Optional. Gets or sets the list of reboot events.
    * @param deploymentEventsValue The DeploymentEvents value.
    */
    public void setDeploymentEvents(final ArrayList<RebootEvent> deploymentEventsValue) {
        this.deploymentEvents = deploymentEventsValue;
    }
    
    /**
    * Initializes a new instance of the DeploymentEventListResponse class.
    *
    */
    public DeploymentEventListResponse() {
        super();
        this.setDeploymentEvents(new LazyArrayList<RebootEvent>());
    }
}
