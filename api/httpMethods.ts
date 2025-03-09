import { EmptyType, ErrorMessage, LoginRequestType, LoginType, PositionRequestType, ProfileRequestType } from "./Types";

export const baseUrl = "";

export async function login(requestBody: LoginType) {
    const response = await fetch(`${baseUrl}/auth/login`, {
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

export async function logout(token: string) {
    const response = await fetch(`${baseUrl}/auth/logout`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            "Authorization": `Bearer ${token}`
        }

    })
    const result: EmptyType | ErrorMessage = await response.json()
    return result
}

export async function getProfile(token: string) {
    const response = await fetch(`${baseUrl}/users/profile`, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            "Authorization": `Bearer ${token}`
        }
    })
    const result: ProfileRequestType | ErrorMessage = await response.json()
    return result
}

export async function getPositions() {
    const response = await fetch(`${baseUrl}/users/positions`, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    })
    const result: PositionRequestType[] | ErrorMessage = await response.json()
    return result
}