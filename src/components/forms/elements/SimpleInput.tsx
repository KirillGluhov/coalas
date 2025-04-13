import { Controller, FieldValues } from "react-hook-form";
import { SimpleInputType } from "./Types";
import { useTheme } from "../../../providers/theme/useTheme";
import { themes } from "../../../providers/theme/ThemeProvider";
import externStyles from '../../../pages/containers/simple-container/login/form/LoginForm.module.scss';

export const SimpleInput = <T extends FieldValues>({control, title, isRequired, name}: SimpleInputType<T>) => {
    const {theme} = useTheme();
    
    return <Controller
        name={name}
        control={control}
        rules={isRequired ? {
            required: `${title} обязательно`,
            validate: (value) => value?.length === 0 || value?.length >= 1 ? true : "Длина хотя бы один символ"
        }: {}}
        render={({field, fieldState}) => (
            <div className={externStyles.inputWithError}>
                <input 
                    type={"text"} 
                    onChange={(e) => {
                        const value = e.target.value;
                        field.onChange(value);
                    }}
                    placeholder={`${isRequired ? '*' : ''} ${title}`}
                    className={`${externStyles.input} ${theme === themes.light ? externStyles.light : externStyles.dark}`}
                />
                <span className={externStyles.error}>{fieldState.error?.message}</span>
            </div>
        )}
    />
}