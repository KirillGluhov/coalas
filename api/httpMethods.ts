import { ErrorMessage, LoginRequestType, LoginType } from "./Types";

export const baseUrl = "http://localhost:8081";

export async function login(requestBody: LoginType) {
    const response = await fetch(`${baseUrl}/login`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(
            {
                password: requestBody.password, 
                ...(requestBody.email ? { email: requestBody.email } : { phone: requestBody.phone })
            }
        )

    })
    const result: LoginRequestType | ErrorMessage = await response.json()
    return result
}