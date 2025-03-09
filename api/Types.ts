export interface ErrorMessage
{
    status: string,
    message?: string | null
}

interface LoginWithEmail {
    password: string;
    email: string;
    phone?: never | null;
}
  
interface LoginWithPhone {
    password: string;
    phone: string;
    email?: never | null; 
}

export type LoginType = LoginWithEmail | LoginWithPhone;

export interface LoginRequestType
{
    accessToken: string,
    refreshToken: string
    userType: "client" | "employee"
}

export interface ProfileRequestType
{
    id: string,
    name: string,
    lastName: string,
    secondName?: string | null,
    gender?: "male" | "female" | null,
    birthDate?: string | null,
    isBlocked: boolean,
    phone?: string | null,
    email?: string | null,
    positionId?: string | null
}

export interface EmptyType
{

}

export interface PositionRequestType
{
    id: string,
    name: string,
    description?: string | null
}