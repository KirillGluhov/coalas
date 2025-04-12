import { combineReducers, configureStore } from "@reduxjs/toolkit";
import { authentificationAPI } from "./services/authentificationService";
import userProfileViewSlice from "./slices/userProfileViewSlice";
import { tariffAPI } from "./services/tariffService";
import { userAPI } from "./services/userService";

export const rootReducer = combineReducers({
    userProfileView: userProfileViewSlice,
    [authentificationAPI.reducerPath]: authentificationAPI.reducer,
    [tariffAPI.reducerPath]: tariffAPI.reducer,
    [userAPI.reducerPath]: userAPI.reducer
})

export const store = configureStore({
    reducer: rootReducer,
    middleware: (getDefaultMiddleware) => getDefaultMiddleware()
    .concat(authentificationAPI.middleware)
    .concat(tariffAPI.middleware)
    .concat(userAPI.middleware)
})