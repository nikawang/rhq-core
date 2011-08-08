/*
 * RHQ Management Platform
 * Copyright (C) 2011 Red Hat, Inc.
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
package org.rhq.core.domain.criteria;

import java.io.Serializable;

import org.rhq.core.domain.drift.DriftChangeSetCategory;
import org.rhq.core.domain.util.PageControl;
import org.rhq.core.domain.util.PageOrdering;

/**
 * The generic implementation of DriftChangeSetCriteria makes no assumptions about the actual drift server
 * plugin that will service the relevant requests.  It is a simple impl of the interface and is
 * suitable for use by any component that can not assume a backend implmentation, like a GUI
 * client.  Server side implementations will use this to populate the plugin-specific implementation
 * of the interface.  
 * 
 * @author Jay Shaughnessy
 * @author John Sanda
 */
public class GenericDriftChangeSetCriteria implements Serializable, DriftChangeSetCriteria {

    private static final long serialVersionUID = 1L;

    private String filterId;

    private String filterVersion;

    private String filterStartVersion;

    private String filterEndVersion;

    private Long filterCreatedAfter;

    private Long filterCreatedBefore;

    private Integer filterResourceId;

    private Integer filterDriftConfigId;

    private DriftChangeSetCategory filterCategory;

    private boolean fetchDrifts;

    private PageOrdering sortVersion;

    private PageControl pageControl;

    @Override
    public void addFilterId(String filterId) {
        this.filterId = filterId;
    }

    @Override
    public String getFilterId() {
        return filterId;
    }

    @Override
    public void addFilterVersion(String filterVersion) {
        this.filterVersion = filterVersion;
    }

    @Override
    public String getFilterVersion() {
        return filterVersion;
    }

    @Override
    public void addFilterStartVersion(String filterStartVersion) {
        this.filterStartVersion = filterStartVersion;
    }

    @Override
    public String getFilterStartVersion() {
        return filterStartVersion;
    }

    @Override
    public void addFilterEndVersion(String filterEndVersion) {
        this.filterEndVersion = filterEndVersion;
    }

    @Override
    public String getFilterEndVersion() {
        return filterEndVersion;
    }

    @Override
    public void addFilterCreatedAfter(Long filterCreatedAfter) {
        this.filterCreatedAfter = filterCreatedAfter;
    }

    @Override
    public Long getFilterCreatedAfter() {
        return filterCreatedAfter;
    }

    @Override
    public void addFilterCreatedBefore(Long filterCreatedBefore) {
        this.filterCreatedBefore = filterCreatedBefore;
    }

    @Override
    public Long getFilterCreatedBefore() {
        return filterCreatedBefore;
    }

    @Override
    public void addFilterResourceId(Integer filterResourceId) {
        this.filterResourceId = filterResourceId;
    }

    @Override
    public Integer getFilterResourceId() {
        return filterResourceId;
    }

    @Override
    public void addFilterDriftConfigurationId(Integer filterDriftConfigId) {
        this.filterDriftConfigId = filterDriftConfigId;
    }

    @Override
    public Integer getFilterDriftConfigurationId() {
        return filterDriftConfigId;
    }

    @Override
    public void addFilterCategory(DriftChangeSetCategory filterCategory) {
        this.filterCategory = filterCategory;
    }

    @Override
    public DriftChangeSetCategory getFilterCategory() {
        return filterCategory;
    }

    @Override
    public void fetchDrifts(boolean fetchDrifts) {
        this.fetchDrifts = fetchDrifts;
    }

    @Override
    public boolean isFetchDrifts() {
        return fetchDrifts;
    }

    @Override
    public void addSortVersion(PageOrdering sortVersion) {
        this.sortVersion = sortVersion;
    }

    @Override
    public PageOrdering getSortVersion() {
        return sortVersion;
    }

    @Override
    public PageControl getPageControlOverrides() {
        return pageControl;
    }

    @Override
    public void setPageControl(PageControl pageControl) {
        this.pageControl = pageControl;
    }
}