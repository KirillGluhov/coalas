export interface ErrorMessage
{
    status: string,
    message?: string
}

interface LoginWithEmail {
    password: string;
    email: string;
    phone?: never;
}
  
interface LoginWithPhone {
    password: string;
    phone: string;
    email?: never; 
}

export type LoginType = LoginWithEmail | LoginWithPhone;

export interface LoginRequestType
{
    accessToken: string,
    refreshToken: string
    userType: "client" | "employee"
}