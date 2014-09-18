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

import java.net.InetAddress;
import java.util.ArrayList;

/**
* The set of parameters required to update a load balanced endpoint set.
*/
public class VirtualMachineUpdateLoadBalancedSetParameters {
    private ArrayList<VirtualMachineUpdateLoadBalancedSetParameters.InputEndpoint> loadBalancedEndpoints;
    
    /**
    * Optional. A list of load balanced InputEndpoints to update.
    * @return The LoadBalancedEndpoints value.
    */
    public ArrayList<VirtualMachineUpdateLoadBalancedSetParameters.InputEndpoint> getLoadBalancedEndpoints() {
        return this.loadBalancedEndpoints;
    }
    
    /**
    * Optional. A list of load balanced InputEndpoints to update.
    * @param loadBalancedEndpointsValue The LoadBalancedEndpoints value.
    */
    public void setLoadBalancedEndpoints(final ArrayList<VirtualMachineUpdateLoadBalancedSetParameters.InputEndpoint> loadBalancedEndpointsValue) {
        this.loadBalancedEndpoints = loadBalancedEndpointsValue;
    }
    
    /**
    * Initializes a new instance of the
    * VirtualMachineUpdateLoadBalancedSetParameters class.
    *
    */
    public VirtualMachineUpdateLoadBalancedSetParameters() {
        this.setLoadBalancedEndpoints(new ArrayList<VirtualMachineUpdateLoadBalancedSetParameters.InputEndpoint>());
    }
    
    /**
    * The modeled external endpoint for a persistent VM role.
    */
    public static class InputEndpoint {
        private Boolean enableDirectServerReturn;
        
        /**
        * Optional. A Boolean specifying whether this endpoint uses Direct
        * Server Return
        * @return The EnableDirectServerReturn value.
        */
        public Boolean isEnableDirectServerReturn() {
            return this.enableDirectServerReturn;
        }
        
        /**
        * Optional. A Boolean specifying whether this endpoint uses Direct
        * Server Return
        * @param enableDirectServerReturnValue The EnableDirectServerReturn
        * value.
        */
        public void setEnableDirectServerReturn(final Boolean enableDirectServerReturnValue) {
            this.enableDirectServerReturn = enableDirectServerReturnValue;
        }
        
        private Integer idleTimeoutInMinutes;
        
        /**
        * Optional. The idle timeout in minutes for this endpoint.
        * @return The IdleTimeoutInMinutes value.
        */
        public Integer getIdleTimeoutInMinutes() {
            return this.idleTimeoutInMinutes;
        }
        
        /**
        * Optional. The idle timeout in minutes for this endpoint.
        * @param idleTimeoutInMinutesValue The IdleTimeoutInMinutes value.
        */
        public void setIdleTimeoutInMinutes(final Integer idleTimeoutInMinutesValue) {
            this.idleTimeoutInMinutes = idleTimeoutInMinutesValue;
        }
        
        private String loadBalancedEndpointSetName;
        
        /**
        * Required. Specifies whether this endpoint is part of shared
        * LoadBalanced endpoint and served by multiple role instances. If not
        * specified a BadRequest error will be returned. It must also be in
        * use by the deployment (at least one role in the deployment must have
        * an endpoint whose LoadBalancedEndpointSetName   matches this)
        * otherwise a BadRequest error will be returned.
        * @return The LoadBalancedEndpointSetName value.
        */
        public String getLoadBalancedEndpointSetName() {
            return this.loadBalancedEndpointSetName;
        }
        
        /**
        * Required. Specifies whether this endpoint is part of shared
        * LoadBalanced endpoint and served by multiple role instances. If not
        * specified a BadRequest error will be returned. It must also be in
        * use by the deployment (at least one role in the deployment must have
        * an endpoint whose LoadBalancedEndpointSetName   matches this)
        * otherwise a BadRequest error will be returned.
        * @param loadBalancedEndpointSetNameValue The
        * LoadBalancedEndpointSetName value.
        */
        public void setLoadBalancedEndpointSetName(final String loadBalancedEndpointSetNameValue) {
            this.loadBalancedEndpointSetName = loadBalancedEndpointSetNameValue;
        }
        
        private String loadBalancerName;
        
        /**
        * Optional. Optional. Specify the name of an internal load balancer if
        * this endpoint shall not be exposed on the default load balancer.
        * @return The LoadBalancerName value.
        */
        public String getLoadBalancerName() {
            return this.loadBalancerName;
        }
        
        /**
        * Optional. Optional. Specify the name of an internal load balancer if
        * this endpoint shall not be exposed on the default load balancer.
        * @param loadBalancerNameValue The LoadBalancerName value.
        */
        public void setLoadBalancerName(final String loadBalancerNameValue) {
            this.loadBalancerName = loadBalancerNameValue;
        }
        
        private LoadBalancerProbe loadBalancerProbe;
        
        /**
        * Optional. This represents an endpoint setting which platform load
        * balancer must monitor to detect the availability of this role before
        * forwarding traffic to this endpoint. If not specified the probe
        * settings (if any) from the existing load balanced endpoint
        * definition  will be retained.
        * @return The LoadBalancerProbe value.
        */
        public LoadBalancerProbe getLoadBalancerProbe() {
            return this.loadBalancerProbe;
        }
        
        /**
        * Optional. This represents an endpoint setting which platform load
        * balancer must monitor to detect the availability of this role before
        * forwarding traffic to this endpoint. If not specified the probe
        * settings (if any) from the existing load balanced endpoint
        * definition  will be retained.
        * @param loadBalancerProbeValue The LoadBalancerProbe value.
        */
        public void setLoadBalancerProbe(final LoadBalancerProbe loadBalancerProbeValue) {
            this.loadBalancerProbe = loadBalancerProbeValue;
        }
        
        private Integer localPort;
        
        /**
        * Optional. Specifies the internal port on which a service running
        * inside the VM is listening to serve this endpoint. WARNING: If
        * specified then ALL the endpoints of this LB set on all the roles
        * will be updated to have THIS local port. To keep unique local ports
        * on each role for a load balanced endpoint specify this as 0 (zero)
        * and if you need to change those use UpdateRole. In case of port
        * conflict with a local port (or probe port) on a role a
        * BadRequestwill be returned.
        * @return The LocalPort value.
        */
        public Integer getLocalPort() {
            return this.localPort;
        }
        
        /**
        * Optional. Specifies the internal port on which a service running
        * inside the VM is listening to serve this endpoint. WARNING: If
        * specified then ALL the endpoints of this LB set on all the roles
        * will be updated to have THIS local port. To keep unique local ports
        * on each role for a load balanced endpoint specify this as 0 (zero)
        * and if you need to change those use UpdateRole. In case of port
        * conflict with a local port (or probe port) on a role a
        * BadRequestwill be returned.
        * @param localPortValue The LocalPort value.
        */
        public void setLocalPort(final Integer localPortValue) {
            this.localPort = localPortValue;
        }
        
        private String name;
        
        /**
        * Optional.  The name of the InputEndpoint.  The name is ignored if
        * specified
        * @return The Name value.
        */
        public String getName() {
            return this.name;
        }
        
        /**
        * Optional.  The name of the InputEndpoint.  The name is ignored if
        * specified
        * @param nameValue The Name value.
        */
        public void setName(final String nameValue) {
            this.name = nameValue;
        }
        
        private Integer port;
        
        /**
        * Optional. An integer specifying the public port for this endpoint.
        * Allowed values are between 1 and 65535 inclusive. A unqiue Port and
        * Protocol combination must be specified for each InputEndpoint in the
        * list.
        * @return The Port value.
        */
        public Integer getPort() {
            return this.port;
        }
        
        /**
        * Optional. An integer specifying the public port for this endpoint.
        * Allowed values are between 1 and 65535 inclusive. A unqiue Port and
        * Protocol combination must be specified for each InputEndpoint in the
        * list.
        * @param portValue The Port value.
        */
        public void setPort(final Integer portValue) {
            this.port = portValue;
        }
        
        private String protocol;
        
        /**
        * Optional. Specifies the transport protocol for the endpoint.
        * @return The Protocol value.
        */
        public String getProtocol() {
            return this.protocol;
        }
        
        /**
        * Optional. Specifies the transport protocol for the endpoint.
        * @param protocolValue The Protocol value.
        */
        public void setProtocol(final String protocolValue) {
            this.protocol = protocolValue;
        }
        
        private ArrayList<AccessControlListRule> rules;
        
        /**
        * Optional. A collection of access control rules which control the
        * external network traffic reaching to this endpoint. NOTES: (1) To
        * remove the ACLs from a load-balanced endpoint just omit this
        * element. (2) ACLs are set as specified. There is no merge done with
        * existing ACLs.
        * @return The Rules value.
        */
        public ArrayList<AccessControlListRule> getRules() {
            return this.rules;
        }
        
        /**
        * Optional. A collection of access control rules which control the
        * external network traffic reaching to this endpoint. NOTES: (1) To
        * remove the ACLs from a load-balanced endpoint just omit this
        * element. (2) ACLs are set as specified. There is no merge done with
        * existing ACLs.
        * @param rulesValue The Rules value.
        */
        public void setRules(final ArrayList<AccessControlListRule> rulesValue) {
            this.rules = rulesValue;
        }
        
        private InetAddress virtualIPAddress;
        
        /**
        * Optional. The virtual IP address of the endpoint.
        * @return The VirtualIPAddress value.
        */
        public InetAddress getVirtualIPAddress() {
            return this.virtualIPAddress;
        }
        
        /**
        * Optional. The virtual IP address of the endpoint.
        * @param virtualIPAddressValue The VirtualIPAddress value.
        */
        public void setVirtualIPAddress(final InetAddress virtualIPAddressValue) {
            this.virtualIPAddress = virtualIPAddressValue;
        }
        
        /**
        * Initializes a new instance of the InputEndpoint class.
        *
        */
        public InputEndpoint() {
            this.setRules(new ArrayList<AccessControlListRule>());
        }
        
        /**
        * Initializes a new instance of the InputEndpoint class with required
        * arguments.
        *
        */
        public InputEndpoint(String loadBalancedEndpointSetName) {
            this();
            if (loadBalancedEndpointSetName == null) {
                throw new NullPointerException("loadBalancedEndpointSetName");
            }
            this.setLoadBalancedEndpointSetName(loadBalancedEndpointSetName);
        }
    }
}
