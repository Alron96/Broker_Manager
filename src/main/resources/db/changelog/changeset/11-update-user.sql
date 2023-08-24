UPDATE user u
    SET u.password = CASE u.id
        WHEN 1 THEN '{bcrypt}$2a$10$1rhrSG/QRr/Vk5h0ALQGRe.uLYkEE0FCtlOsd7/TaM.2M82txtdXW'
        WHEN 2 THEN '{bcrypt}$2a$10$IKAetPOjsWit8SPRZ/pR6ukr9lNTDRuPG7yxr7ydFqhb1eNL02IVq'
        WHEN 3 THEN '{bcrypt}$2a$10$2tzxnViIewGpPb7ARZ.BLeeg0BKSV93tI7jabg8LDTYy94kTmYT.q'
        WHEN 4 THEN '{bcrypt}$2a$10$RY2cB0bsGCDFJhKxAsO.veKVYV7qOG3ZjK/AUdNWP7l3bWbOW.Wsy'
        WHEN 5 THEN '{bcrypt}$2a$10$pINMEEqn7IUrxJnT5KIDmuIt5kfYnrze/O7Ca9PjRPifVrFykltdG'
        WHEN 6 THEN '{bcrypt}$2a$10$0neQEnQ5JVO0TE3r7ijscOdT7Zdd5pd0hLn0vh7B1lyDOLD8hdree'
        WHEN 7 THEN '{bcrypt}$2a$10$W/F76TuLxFzMVF.xodpi1uyHIG2AxTjXtgTIrNOqfx4ocsvIytAYm'
    END
WHERE u.id IN (1, 2, 3, 4, 5, 6, 7)
