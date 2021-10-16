package models

import java.util.UUID


case class CommentItem(id: UUID, user: String, content: String)