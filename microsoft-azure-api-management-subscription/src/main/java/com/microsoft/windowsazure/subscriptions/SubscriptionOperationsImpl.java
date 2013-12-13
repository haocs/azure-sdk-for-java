// 
// Copyright (c) Microsoft and contributors.  All rights reserved.
// 
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//   http://www.apache.org/licenses/LICENSE-2.0
// 
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// 
// See the License for the specific language governing permissions and
// limitations under the License.
// 

// Warning: This code was generated by a tool.
// 
// Changes to this file may cause incorrect behavior and will be lost if the
// code is regenerated.

package com.microsoft.windowsazure.subscriptions;

import com.microsoft.windowsazure.services.core.ServiceException;
import com.microsoft.windowsazure.services.core.ServiceOperations;
import com.microsoft.windowsazure.subscriptions.models.SubscriptionListOperationResponse;
import com.microsoft.windowsazure.subscriptions.models.SubscriptionStatus;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class SubscriptionOperationsImpl implements ServiceOperations<SubscriptionClientImpl>, SubscriptionOperations
{
    /**
    * Initializes a new instance of the SubscriptionOperationsImpl class.
    *
    * @param client Reference to the service client.
    */
    SubscriptionOperationsImpl(SubscriptionClientImpl client)
    {
        this.client = client;
    }
    
    private SubscriptionClientImpl client;
    
    /**
    * Gets a reference to the
    * microsoft.windowsazure.subscriptions.SubscriptionClientImpl.
    */
    public SubscriptionClientImpl getClient() { return this.client; }
    
    /**
    *
    * @return A standard service response including an HTTP status code and
    * request ID.
    */
    @Override
    public Future<SubscriptionListOperationResponse> listAsync()
    {
        return this.getClient().getExecutorService().submit(new Callable<SubscriptionListOperationResponse>() { @Override
        public SubscriptionListOperationResponse call() throws Exception
        {
            return list();
        }
         });
    }
    
    /**
    *
    * @return A standard service response including an HTTP status code and
    * request ID.
    */
    @Override
    public SubscriptionListOperationResponse list() throws IOException, ServiceException, ParserConfigurationException, SAXException, ParseException
    {
        // Validate
        
        // Tracing
        
        // Construct URL
        String url = this.getClient().getBaseUri() + "/subscriptions";
        
        // Create HTTP transport objects
        HttpGet httpRequest = new HttpGet(url);
        
        // Set Headers
        httpRequest.setHeader("Content-Type", "application/xml;charset=utf-8");
        httpRequest.setHeader("x-ms-version", "2013-08-01");
        
        // Send Request
        HttpResponse httpResponse = null;
        httpResponse = this.getClient().getHttpClient().execute(httpRequest);
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        if (statusCode != 200)
        {
            ServiceException ex = ServiceException.createFromXml(httpRequest, null, httpResponse, httpResponse.getEntity());
            throw ex;
        }
        
        // Create Result
        SubscriptionListOperationResponse result = null;
        // Deserialize Response
        InputStream responseContent = httpResponse.getEntity().getContent();
        result = new SubscriptionListOperationResponse();
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document responseDoc = documentBuilder.parse(responseContent);
        
        NodeList elements = responseDoc.getElementsByTagName("Subscriptions");
        Element subscriptionsElement = elements.getLength() > 0 ? ((Element)elements.item(0)) : null;
        if (subscriptionsElement != null)
        {
            if (subscriptionsElement != null)
            {
                for (int i1 = 0; i1 < subscriptionsElement.getElementsByTagName("Subscription").getLength(); i1 = i1 + 1)
                {
                    org.w3c.dom.Element subscriptionsElement2 = ((org.w3c.dom.Element)subscriptionsElement.getElementsByTagName("Subscription").item(i1));
                    SubscriptionListOperationResponse.Subscription subscriptionInstance = new SubscriptionListOperationResponse.Subscription();
                    result.getSubscriptions().add(subscriptionInstance);
                    
                    NodeList elements2 = subscriptionsElement2.getElementsByTagName("SubscriptionID");
                    Element subscriptionIDElement = elements2.getLength() > 0 ? ((Element)elements2.item(0)) : null;
                    if (subscriptionIDElement != null)
                    {
                        String subscriptionIDInstance;
                        subscriptionIDInstance = subscriptionIDElement.getTextContent();
                        subscriptionInstance.setSubscriptionId(subscriptionIDInstance);
                    }
                    
                    NodeList elements3 = subscriptionsElement2.getElementsByTagName("SubscriptionName");
                    Element subscriptionNameElement = elements3.getLength() > 0 ? ((Element)elements3.item(0)) : null;
                    if (subscriptionNameElement != null)
                    {
                        String subscriptionNameInstance;
                        subscriptionNameInstance = subscriptionNameElement.getTextContent();
                        subscriptionInstance.setSubscriptionName(subscriptionNameInstance);
                    }
                    
                    NodeList elements4 = subscriptionsElement2.getElementsByTagName("SubscriptionStatus");
                    Element subscriptionStatusElement = elements4.getLength() > 0 ? ((Element)elements4.item(0)) : null;
                    if (subscriptionStatusElement != null)
                    {
                        SubscriptionStatus subscriptionStatusInstance;
                        subscriptionStatusInstance = SubscriptionStatus.valueOf(subscriptionStatusElement.getTextContent());
                        subscriptionInstance.setSubscriptionStatus(subscriptionStatusInstance);
                    }
                    
                    NodeList elements5 = subscriptionsElement2.getElementsByTagName("AccountAdminLiveEmailId");
                    Element accountAdminLiveEmailIdElement = elements5.getLength() > 0 ? ((Element)elements5.item(0)) : null;
                    if (accountAdminLiveEmailIdElement != null)
                    {
                        String accountAdminLiveEmailIdInstance;
                        accountAdminLiveEmailIdInstance = accountAdminLiveEmailIdElement.getTextContent();
                        subscriptionInstance.setAccountAdminLiveEmailId(accountAdminLiveEmailIdInstance);
                    }
                    
                    NodeList elements6 = subscriptionsElement2.getElementsByTagName("ServiceAdminLiveEmailId");
                    Element serviceAdminLiveEmailIdElement = elements6.getLength() > 0 ? ((Element)elements6.item(0)) : null;
                    if (serviceAdminLiveEmailIdElement != null)
                    {
                        String serviceAdminLiveEmailIdInstance;
                        serviceAdminLiveEmailIdInstance = serviceAdminLiveEmailIdElement.getTextContent();
                        subscriptionInstance.setServiceAdminLiveEmailId(serviceAdminLiveEmailIdInstance);
                    }
                    
                    NodeList elements7 = subscriptionsElement2.getElementsByTagName("MaxCoreCount");
                    Element maxCoreCountElement = elements7.getLength() > 0 ? ((Element)elements7.item(0)) : null;
                    if (maxCoreCountElement != null)
                    {
                        int maxCoreCountInstance;
                        maxCoreCountInstance = Integer.parseInt(maxCoreCountElement.getTextContent());
                        subscriptionInstance.setMaximumCoreCount(maxCoreCountInstance);
                    }
                    
                    NodeList elements8 = subscriptionsElement2.getElementsByTagName("MaxStorageAccounts");
                    Element maxStorageAccountsElement = elements8.getLength() > 0 ? ((Element)elements8.item(0)) : null;
                    if (maxStorageAccountsElement != null)
                    {
                        int maxStorageAccountsInstance;
                        maxStorageAccountsInstance = Integer.parseInt(maxStorageAccountsElement.getTextContent());
                        subscriptionInstance.setMaximumStorageAccounts(maxStorageAccountsInstance);
                    }
                    
                    NodeList elements9 = subscriptionsElement2.getElementsByTagName("MaxHostedServices");
                    Element maxHostedServicesElement = elements9.getLength() > 0 ? ((Element)elements9.item(0)) : null;
                    if (maxHostedServicesElement != null)
                    {
                        int maxHostedServicesInstance;
                        maxHostedServicesInstance = Integer.parseInt(maxHostedServicesElement.getTextContent());
                        subscriptionInstance.setMaximumHostedServices(maxHostedServicesInstance);
                    }
                    
                    NodeList elements10 = subscriptionsElement2.getElementsByTagName("CurrentCoreCount");
                    Element currentCoreCountElement = elements10.getLength() > 0 ? ((Element)elements10.item(0)) : null;
                    if (currentCoreCountElement != null)
                    {
                        int currentCoreCountInstance;
                        currentCoreCountInstance = Integer.parseInt(currentCoreCountElement.getTextContent());
                        subscriptionInstance.setCurrentCoreCount(currentCoreCountInstance);
                    }
                    
                    NodeList elements11 = subscriptionsElement2.getElementsByTagName("CurrentStorageAccounts");
                    Element currentStorageAccountsElement = elements11.getLength() > 0 ? ((Element)elements11.item(0)) : null;
                    if (currentStorageAccountsElement != null)
                    {
                        int currentStorageAccountsInstance;
                        currentStorageAccountsInstance = Integer.parseInt(currentStorageAccountsElement.getTextContent());
                        subscriptionInstance.setCurrentStorageAccounts(currentStorageAccountsInstance);
                    }
                    
                    NodeList elements12 = subscriptionsElement2.getElementsByTagName("CurrentHostedServices");
                    Element currentHostedServicesElement = elements12.getLength() > 0 ? ((Element)elements12.item(0)) : null;
                    if (currentHostedServicesElement != null)
                    {
                        int currentHostedServicesInstance;
                        currentHostedServicesInstance = Integer.parseInt(currentHostedServicesElement.getTextContent());
                        subscriptionInstance.setCurrentHostedServices(currentHostedServicesInstance);
                    }
                    
                    NodeList elements13 = subscriptionsElement2.getElementsByTagName("MaxVirtualNetworkSites");
                    Element maxVirtualNetworkSitesElement = elements13.getLength() > 0 ? ((Element)elements13.item(0)) : null;
                    if (maxVirtualNetworkSitesElement != null)
                    {
                        int maxVirtualNetworkSitesInstance;
                        maxVirtualNetworkSitesInstance = Integer.parseInt(maxVirtualNetworkSitesElement.getTextContent());
                        subscriptionInstance.setMaximumVirtualNetworkSites(maxVirtualNetworkSitesInstance);
                    }
                    
                    NodeList elements14 = subscriptionsElement2.getElementsByTagName("MaxLocalNetworkSites");
                    Element maxLocalNetworkSitesElement = elements14.getLength() > 0 ? ((Element)elements14.item(0)) : null;
                    if (maxLocalNetworkSitesElement != null)
                    {
                        int maxLocalNetworkSitesInstance;
                        maxLocalNetworkSitesInstance = Integer.parseInt(maxLocalNetworkSitesElement.getTextContent());
                        subscriptionInstance.setMaximumLocalNetworkSites(maxLocalNetworkSitesInstance);
                    }
                    
                    NodeList elements15 = subscriptionsElement2.getElementsByTagName("MaxDnsServers");
                    Element maxDnsServersElement = elements15.getLength() > 0 ? ((Element)elements15.item(0)) : null;
                    if (maxDnsServersElement != null)
                    {
                        int maxDnsServersInstance;
                        maxDnsServersInstance = Integer.parseInt(maxDnsServersElement.getTextContent());
                        subscriptionInstance.setMaximumDnsServers(maxDnsServersInstance);
                    }
                    
                    NodeList elements16 = subscriptionsElement2.getElementsByTagName("MaxExtraVIPCount");
                    Element maxExtraVIPCountElement = elements16.getLength() > 0 ? ((Element)elements16.item(0)) : null;
                    if (maxExtraVIPCountElement != null)
                    {
                        int maxExtraVIPCountInstance;
                        maxExtraVIPCountInstance = Integer.parseInt(maxExtraVIPCountElement.getTextContent());
                        subscriptionInstance.setMaximumExtraVirtualIPCount(maxExtraVIPCountInstance);
                    }
                    
                    NodeList elements17 = subscriptionsElement2.getElementsByTagName("AADTenantID");
                    Element aADTenantIDElement = elements17.getLength() > 0 ? ((Element)elements17.item(0)) : null;
                    if (aADTenantIDElement != null)
                    {
                        String aADTenantIDInstance;
                        aADTenantIDInstance = aADTenantIDElement.getTextContent();
                        subscriptionInstance.setActiveDirectoryTenantId(aADTenantIDInstance);
                    }
                    
                    NodeList elements18 = subscriptionsElement2.getElementsByTagName("CreatedTime");
                    Element createdTimeElement = elements18.getLength() > 0 ? ((Element)elements18.item(0)) : null;
                    if (createdTimeElement != null)
                    {
                        Calendar createdTimeInstance;
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(simpleDateFormat.parse(createdTimeElement.getTextContent()));
                        createdTimeInstance = calendar;
                        subscriptionInstance.setCreated(createdTimeInstance);
                    }
                }
            }
        }
        
        result.setStatusCode(statusCode);
        if (httpResponse.getHeaders("x-ms-request-id").length > 0)
        {
            result.setRequestId(httpResponse.getFirstHeader("x-ms-request-id").getValue());
        }
        
        return result;
    }
}