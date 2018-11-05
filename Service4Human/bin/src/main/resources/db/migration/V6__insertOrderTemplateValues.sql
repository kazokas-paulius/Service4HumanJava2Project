INSERT INTO user.order_template VALUES (
null, null, 
null,
"lietuviu kalba", "persiku kalba",
null, null,
null, null
);

UPDATE user.order_template ot, user.user_data u
  SET ot.users_id = u.id,
      ot.order_date = CURRENT_DATE();