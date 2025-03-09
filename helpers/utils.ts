export const formatPhone = (text: string, setError: React.Dispatch<React.SetStateAction<boolean>>) =>
{
    const numbersOnly = text.replace(/\D/g, '').slice(0, 18);
    if (numbersOnly.length < 8)
    {
        setError(true);
    }
    else
    {
        setError(false)
    }

    if ((numbersOnly.length >= 8 && numbersOnly.length <= 18) || numbersOnly.length === 0)
    {
        setError(false);
    }
    else
    {
        setError(true)
    }

    return numbersOnly.length > 0 ? `+${numbersOnly}` : '';
};

export const formatEmail = (text: string, setError: React.Dispatch<React.SetStateAction<boolean>>) => {
    const emailRegex = /^((?:[A-Za-z0-9!#$%&'*+\-\/=?^_`{|}~]|(?<=^|\.)"|"(?=$|\.|@)|(?<=".*)[ .](?=.*")|(?<!\.)\.){1,64})(@)((?:[A-Za-z0-9.\-])*(?:[A-Za-z0-9])\.(?:[A-Za-z0-9]){2,})$/;

    if (emailRegex.test(text) || text.length === 0)
    {
        setError(false);
    }
    else
    {
        setError(true)
    }

    return text;
}

export const formatPassword = (text: string, setError: React.Dispatch<React.SetStateAction<boolean>>) =>
{
    if (text.length <= 0)
    {
        setError(true)
    }
    else
    {
        setError(false)
    }
    return text;
}