import { AuthType } from "./services/types/dataTypes";
import { rootReducer, store } from "./store";

export interface UserProfile {
    authData?: AuthType
}

export type RootState = ReturnType<typeof rootReducer>
export type AppDispatch = typeof store.dispatch;
