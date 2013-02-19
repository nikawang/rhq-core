package org.rhq.enterprise.gui.coregui.client.util.selenium;

import com.smartgwt.client.widgets.layout.HLayout;

/**
 * Wrapper for a SmartGWT {@link HLayout} that sets the ID for use with Selenium scLocators.
 * 
 * @author Jay Shaughnessy
 */
public class LocatableHLayout extends HLayout implements Locatable {

    private String locatorId;

    /** 
     * <pre>
     * ID Format: "simpleClassname_locatorId"
     * </pre>
     * @param locatorId not null or empty.
     */
    public LocatableHLayout(String locatorId) {
        super();
    }

    public LocatableHLayout() {
        super();
    }

    /** 
     * <pre>
     * ID Format: "simpleClassname_locatorId"
     * </pre>
     * @param locatorId not null or empty.
     * @param membersMargin
     */
    public LocatableHLayout(int membersMargin) {
        super(membersMargin);
    }


    public String getLocatorId() {
        return locatorId;
    }

    public String extendLocatorId(String extension) {
        return this.locatorId + "_" + extension;
    }

    public void destroyMembers() {
        SeleniumUtility.destroyMembers(this);
    }

    @Override
    public void destroy() {
        destroyMembers();
        super.destroy();
    }

}
