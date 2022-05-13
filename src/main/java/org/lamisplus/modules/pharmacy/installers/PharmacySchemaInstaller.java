package org.lamisplus.modules.pharmacy.installers;

import com.foreach.across.core.annotations.Installer;
import com.foreach.across.core.installers.AcrossLiquibaseInstaller;
import org.springframework.core.annotation.Order;

@Order(1)
@Installer(name = "pharmacy-schema-installer",
        description = "Installs the required pharmacy database tables",
        version = 1)
public class PharmacySchemaInstaller extends AcrossLiquibaseInstaller {
    public PharmacySchemaInstaller() {
        super("classpath:schema/pharmacy-schema.xml");
    }
}
