import { ProfileType, UserType } from "../../store/services/types/dataTypes";

export function getDisplayName(data: ProfileType | UserType)
{
    const {name, secondName} = data;

    return `${name} ${secondName}`;
}