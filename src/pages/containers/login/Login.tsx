import { Card } from "../../../components/card/Card";
import { LoginForm } from "./form/LoginForm";

export const Login = () => {
    return <Card title={"Вход"}>
        <LoginForm/>
    </Card>;
}