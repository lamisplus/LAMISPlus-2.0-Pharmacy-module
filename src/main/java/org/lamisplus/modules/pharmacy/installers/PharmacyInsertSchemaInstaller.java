package org.lamisplus.modules.pharmacy.installers;

import com.foreach.across.core.annotations.Installer;
import com.foreach.across.core.installers.AcrossLiquibaseInstaller;
import org.springframework.core.annotation.Order;

@Order(2)
@Installer(name = "pharmacy-insert-schema-installer",
        description = "Insert the required database tables data for pharmacy",
        version = 1)
public class PharmacyInsertSchemaInstaller extends AcrossLiquibaseInstaller {
    public PharmacyInsertSchemaInstaller() {
        super("classpath:schema/pharmacy-insert-schema-1.0.xml");
    }
}
