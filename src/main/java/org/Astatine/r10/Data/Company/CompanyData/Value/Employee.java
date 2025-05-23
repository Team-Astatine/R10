package org.Astatine.r10.Data.Company.CompanyData.Value;

import org.Astatine.r10.Data.Company.CompanyData.Enumeration.Position;

import java.time.LocalDateTime;
import java.util.UUID;

public record Employee(
    UUID affiliatedCompanyUUID,
    LocalDateTime dateOfJoining,
    LocalDateTime dateOfLeaving,
    Position position
) {}
