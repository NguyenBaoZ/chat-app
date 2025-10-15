export interface ChatMessage {
  id: string;
  text: string;
  timestamp: Date;
  status: 'sending' | 'sent' | 'seen' | 'error';
  isOwnMessage: boolean;
  reactions?: string[];
}