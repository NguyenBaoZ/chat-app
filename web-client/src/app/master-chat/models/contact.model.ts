export interface Contact {
  id: string;
  name: string;
  email: string;
  avatar?: string;
  phone?: string;
  company?: string;
  position?: string;
  lastMessage?: string;
  lastMessageTime?: Date;
}