import { User } from "./user.model";

export interface JwtToken {
  token: string
  user: User
}
