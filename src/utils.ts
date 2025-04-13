import { genders, GenderType, PositionType, ProfileType, UserType } from "./store/services/types/dataTypes";
import Female from './assets/icons/female.svg';
import Male from './assets/icons/male.svg';

const NULL_STRING = "отсутствует";

export function getDisplayName(data: ProfileType | UserType)
{
    const {name, secondName} = data;

    return `${name} ${secondName}`;
}

export function getFullName(data: ProfileType | UserType)
{
    const {name, secondName, lastName} = data;

    return `${name} ${secondName} ${lastName}`;
}

export function getGenderLogo(gender: GenderType | undefined)
{
    const logos = {
        [genders.male]: Male,
        [genders.female]: Female
    }

    return gender ? logos[gender] : gender;
}

export function getPosition(position?: PositionType)
{
    return position ? position.name : NULL_STRING
}