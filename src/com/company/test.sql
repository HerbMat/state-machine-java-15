SELECT play.id as id, play.title as title, COALESCE(SUM(reservations.number_of_tickets), 0) as total_number_of_tickets
FROM plays
FULL JOIN reservations ON (plays.id = reservations.play_id)
GROUP BY play.id
ORDER BY total_number_of_tickets DESC,
         id ASC;
