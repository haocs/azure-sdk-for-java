/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 * 
 * Code generated by Microsoft (R) AutoRest Code Generator 0.13.0.0
 * Changes may cause incorrect behavior and will be lost if the code is
 * regenerated.
 */

package com.microsoft.azure.management.network.models;

import com.microsoft.rest.BaseResource;

/**
 * The arp table associated with the ExpressRouteCircuit
 */
public class ExpressRouteCircuitArpTable {
    /**
     * Gets ipAddress.
     */
    private String ipAddress;

    /**
     * Gets macAddress.
     */
    private String macAddress;

    /**
     * Get the ipAddress value.
     *
     * @return the ipAddress value
     */ 
    public String getIpAddress() {
        return this.ipAddress;
    }

    /**
     * Set the ipAddress value.
     *
     * @param ipAddress the ipAddress value to set
     */
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    /**
     * Get the macAddress value.
     *
     * @return the macAddress value
     */ 
    public String getMacAddress() {
        return this.macAddress;
    }

    /**
     * Set the macAddress value.
     *
     * @param macAddress the macAddress value to set
     */
    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

}