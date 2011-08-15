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

package org.rhq.core.pc.drift;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.rhq.core.domain.drift.Filter;
import org.rhq.core.util.file.FileVisitor;
import org.rhq.core.util.file.PathFilter;

import static org.apache.commons.io.FilenameUtils.normalize;
import static org.rhq.core.util.file.FileUtil.generateRegex;

public class FilterFileVisitor implements FileVisitor {

    private List<PathFilter> includes;

    private List<PathFilter> excludes;

    private FileVisitor visitor;

    private Pattern includesPattern;

    private Pattern excludesPattern;

    public FilterFileVisitor(File basedir, List<Filter> includes, List<Filter> excludes, FileVisitor visitor) {
        this.includes = convert(basedir, includes);
        this.excludes = convert(basedir, excludes);
        this.visitor = visitor;
        includesPattern = generateRegex(this.includes);
        excludesPattern = generateRegex(this.excludes);
    }

    private List<PathFilter> convert(File basedir, List<Filter> filters) {
        List<PathFilter> pathFilters = new ArrayList<PathFilter>(filters.size());
        for (Filter filter : filters) {
            File path = new File(filter.getPath());
            if (path.isAbsolute()) {
                pathFilters.add(new PathFilter(normalize(path.getAbsolutePath()), filter.getPattern()));
            } else {
                pathFilters.add(new PathFilter(normalize(new File(basedir, filter.getPath()).getAbsolutePath()),
                filter.getPattern()));
            }
        }
        return pathFilters;
    }

    @Override
    public void visit(File file) {
        if (includes.isEmpty() && excludes.isEmpty()) {
            visitor.visit(file);
        } else if (!includes.isEmpty() && excludes.isEmpty()) {
            if (includesPattern.matcher(file.getAbsolutePath()).matches()) {
                visitor.visit(file);
            }
        } else if (includes.isEmpty() && !excludes.isEmpty()) {
            if (!excludesPattern.matcher(file.getAbsolutePath()).matches()) {
                visitor.visit(file);
            }
        } else {
            // else neither includes nor excludes is empty
            if (includesPattern.matcher(file.getAbsolutePath()).matches() &&
                !excludesPattern.matcher(file.getAbsolutePath()).matches()) {
                visitor.visit(file);
            }
        }
    }
}
