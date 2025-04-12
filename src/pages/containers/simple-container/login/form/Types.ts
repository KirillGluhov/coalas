interface Phone {
    phone: string;
}

interface Email{
    email: string
}

interface Password
{
    password: string
}

export type OptionalLoginForm = (Email | Phone) & Password

export type LoginFormType = {
    password: string,
    phone: string,
    email: string
}