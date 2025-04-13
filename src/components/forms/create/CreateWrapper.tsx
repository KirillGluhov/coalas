import { FC, useState } from "react";
import { Modal } from "../../modal/Modal";
import { Button } from "../../button/Button";
import { ButtonStyleType } from "../../button/Types";
import { CreateWrapperType } from "./Types";

export const CreateWrapper: FC<CreateWrapperType> = ({Form}) => {
    const [open, setOpen] = useState<boolean>(false);
    
    return <div>
        <Button text={"Создать"} 
            type={ButtonStyleType.Create} 
            onClick={() => setOpen(true)}
        />
        <Modal open={open} setOpen={setOpen}>
            <Form close={setOpen}/>
        </Modal>
    </div>;
}