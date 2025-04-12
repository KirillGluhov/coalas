import { createSlice, PayloadAction } from "@reduxjs/toolkit/react";
import { UserProfile } from "../storeTypes";
import { AuthType, TokenType } from "../services/types/dataTypes";

const initialState: UserProfile = {
    authData: undefined
}

const userProfileViewSlice = createSlice({
    name: "user",
    initialState,
    reducers: {
        setAuthData(state, action: PayloadAction<AuthType>)
        {
            state.authData = action.payload;
        },

        clearAuthData(state)
        {
            state.authData = undefined
        },

        renewAuthData(state, action: PayloadAction<TokenType>)
        {
            state.authData = {
                ...state.authData,
                accessToken: action.payload.accessToken,
                refreshToken: action.payload.refreshToken
            }
        }
    }
})

export const {setAuthData, clearAuthData} = userProfileViewSlice.actions
export default userProfileViewSlice.reducer