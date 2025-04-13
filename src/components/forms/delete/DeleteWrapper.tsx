import { FC, useState } from "react";
import { DeleteWrapperType } from "./Types";
import { Button } from "../../button/Button";
import { ButtonStyleType } from "../../button/Types";
import { Modal } from "../../modal/Modal";
import { DeleteForm } from "./DeleteForm";

export const DeleteWrapper: FC<DeleteWrapperType> = ({id, title, deleteEntity}) => {
    const [open, setOpen] = useState<boolean>(false);

    return <div>
        <Button 
            text={"Удалить"} 
            type={ButtonStyleType.Delete}
            onClick={() => setOpen(true)}
        />
        <Modal open={open} setOpen={setOpen}>
            <DeleteForm close={setOpen} id={id} deleteEntity={deleteEntity} title={title}/>
        </Modal>
    </div>
}