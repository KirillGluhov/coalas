export type UserStatusType = "EMPLOYEE" | "CLIENT"
export type GenderType = "MALE" | "FEMALE"

export interface RefreshTokenType
{
    refreshToken?: string,
}

export interface TokenType extends RefreshTokenType
{
    accessToken?: string,
}

export interface AuthType extends TokenType
{
    userType?: UserStatusType
}


export interface ProfileType {
    id: string,
    name: string | undefined,
    lastName: string | undefined,
    secondName: string | undefined,
    gender: GenderType | undefined,
    birthDate: string | undefined,
    email: string | undefined,
    phone: string | undefined,
    passportId: string | undefined,
    positionId: string | undefined,
    blocked: boolean | undefined
}

export interface TariffFormType
{
    name: string,
    procent: number
}

export interface TariffType {
    name: string | undefined,
    procent: number | undefined
    id: string,
    employeeId: string | undefined,
}

export interface UserType {
    id: string,
    name: string | undefined,
    lastName: string | undefined,
    secondName: string | undefined,
    gender: GenderType | undefined,
    birthDate: string | undefined,
    email: string | undefined,
    phone: string | undefined,
    passportId: string | undefined,
    positionId: string | undefined,
    blocked: boolean | undefined
}

export interface PositionType
{
    id: string,
    name: string | undefined,
    description: string | undefined
}