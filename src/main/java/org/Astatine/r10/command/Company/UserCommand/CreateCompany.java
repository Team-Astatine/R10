package org.Astatine.r10.command.Company.UserCommand;

import org.Astatine.r10.Data.Company.CompanyData.Enumeration.BusinessSize;
import org.Astatine.r10.Data.Company.CompanyData.Enumeration.Position;
import org.Astatine.r10.Data.Company.CompanyData.Value.Company;
import org.Astatine.r10.Data.Company.CompanyData.Value.CompanyController;
import org.Astatine.r10.Data.Company.CompanyData.Value.Employee;
import org.Astatine.r10.Enumeration.Type.ColorType;
import org.Astatine.r10.Util.Function.EssentialsUtil;
import org.Astatine.r10.command.CommandRegisterSection;
import org.Astatine.r10.command.ListOfCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.UUID;

public class CreateCompany extends CommandRegisterSection {

    public CreateCompany() {
        super(ListOfCommand.CREATE_COMPANY);
    }

    @Override
    public boolean onCommand(final @NotNull CommandSender sender,
                             final @NotNull Command command,
                             final @NotNull String label,
                             final @NotNull String[] args) {

        System.out.println(Arrays.toString(args));

        UUID companyUUID = UUID.randomUUID();
        Player companyOwner = (Player) sender;

        BigDecimal companyBalance = new EssentialsUtil().getEssentialPluginUserMoney(companyOwner);
        Employee owner = new Employee(
                companyOwner.getUniqueId(),
                companyUUID,
                new Date(),
                Position.OWNER
        );

        HashSet<Employee> employees = new HashSet<>();
        employees.add(owner);

        Company newCompany = new Company(
                UUID.randomUUID(),
                companyOwner.getUniqueId(),
                args[0],
                companyBalance,
                BusinessSize.STARTUP,
                employees
        );

        new CompanyController().createCompany(newCompany);

        return true;
    }
}
