export class User {
    id: number;
    username: string;
    phone_number: string;
    email: string;
    area: string;
    status: number;
    authorized: number;
    fk_roles: number;
    creator_user: number;
    modifier_user: number;
    creation_date: Date;
    update_date: Date;
}