import { LoginFormType, OptionalLoginForm } from "./Types";

export function toOptionalLoginForm(data: LoginFormType): OptionalLoginForm
{
    const {phone, email, password} = data;

    if (email)
    {
        return {email, password}
    }
    else
    {
        return {phone, password}
    }
}