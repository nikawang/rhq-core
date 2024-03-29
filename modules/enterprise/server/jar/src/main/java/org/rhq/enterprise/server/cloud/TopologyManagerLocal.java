/*
 * RHQ Management Platform
 * Copyright (C) 2005-2008 Red Hat, Inc.
 * All rights reserved.
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation version 2 of the License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 */
package org.rhq.enterprise.server.cloud;

import java.util.List;

import javax.ejb.Local;

import org.rhq.core.domain.auth.Subject;
import org.rhq.core.domain.cloud.FailoverListDetails;
import org.rhq.core.domain.cloud.Server;
import org.rhq.core.domain.cloud.composite.ServerWithAgentCountComposite;
import org.rhq.core.domain.criteria.ServerCriteria;
import org.rhq.core.domain.resource.Agent;
import org.rhq.core.domain.util.PageControl;
import org.rhq.core.domain.util.PageList;

/**
 * @author Joseph Marques
 */
@Local
public interface TopologyManagerLocal {

    void deleteServers(Subject subject, Integer[] serverIds) throws TopologyManagerException;

    void deleteServer(Subject subject, Integer serverId) throws TopologyManagerException;

    List<Agent> getAgentsByServerName(String serverName);

    /**
     * 
     * @param serverId
     * @return the server
     * @deprecated Use the <code>findServersByCriteria()</code> instead.
     */
    Server getServerById(int serverId);

    /**
     * 
     * @param serverName
     * @return the server
     * @deprecated Use the <code>findServersByCriteria()</code> instead.
     */
    Server getServerByName(String serverName);

    /**
     * Return every server registered in the database considered part of the active cloud. This will exclude, for example,
     * servers in INSTALLED mode or any other mode that should not be included when performing cloud operations such as partitioning.
     * @return All servers available to the cloud.
     * 
     */
    List<Server> getAllCloudServers();

    /**
     * Return every server registered in the database.
     * @return All servers, regardless of operation mode.
     * @deprecated Use the <code>findServersByCriteria()</code> instead.
     */
    List<Server> getAllServers();

    
    /**
     * Returns all servers with the agent counts
     * 
     * the subject needs to have MANAGE_INVENTORY and MANAGE_SETTINGS permissions.
     * 
     * @param subject the caller
     * @param pc
     * @return list of <code>ServerWithAgentCountComposite</code>
     */
    PageList<ServerWithAgentCountComposite> getServerComposites(Subject subject, PageControl pc);

    /**
     * Returns the number of servers that are part of the "server cloud", which excludes
     * servers that have been installed but not associated with the cloud.
     * The returned count will include those servers that are down or in maintenance mode,
     * in addition to those servers that are currently running in a normal state.
     * 
     * @return count of servers in the cloud
     */
    int getServerCount();

    /**
     * Returns the number of servers that are part of the "server cloud" that are currently
     * running in "normal" mode.
     * 
     * This excludes all other servers such as those servers that have been installed but
     * not associated with the cloud, servers that are down or in maintenance mode.
     * 
     * @return count of servers in the cloud that are in a normal running state
     */
    int getNormalServerCount();

    /**
     * Updates the server operation mode for multiple servers.
     * 
     * the subject needs to have MANAGE_INVENTORY and MANAGE_SETTINGS permissions.
     * 
     * @param subject the caller
     * @param serverIds
     * @param mode desired server operation mode
     */
    void updateServerMode(Subject subject, Integer[] serverIds, Server.OperationMode mode);

    /**
     * Updates the server.
     * 
     * the subject needs to have MANAGE_INVENTORY and MANAGE_SETTINGS permissions.
     * 
     * @param subject the caller.
     * @param server
     * @return
     */
    Server updateServer(Subject subject, Server server);

    /**
     * 
     * the subject needs to have MANAGE_SETTINGS permissions.
     * 
     * @param subject
     * @param agentId
     * @param pc
     * @return
     */
    PageList<FailoverListDetails> getFailoverListDetailsByAgentId(Subject subject, int agentId, PageControl pc);

    void markStaleServersDown(Subject subject);

    /**
     * Fetches the server based on provided criteria.
     * 
     * the subject needs to have MANAGE_SETTINGS permissions.
     * 
     * @param subject caller
     * @param criteria the criteria
     * @return list of servers
     */
    PageList<Server> findServersByCriteria(Subject subject, ServerCriteria criteria);
}
