CREATE OR REPLACE FUNCTION aoc.random_answer()
  RETURNS integer AS
$BODY$
DECLARE
	v_rand numeric;
BEGIN
	v_rand := random();

	IF v_rand < 0.3 THEN
		RETURN -1;
	ELSIF v_rand < 0.5 THEN
		RETURN 0;
	ELSE 
		RETURN 1;
	END IF;
END;
$BODY$
  LANGUAGE plpgsql VOLATILE;






CREATE OR REPLACE FUNCTION aoc.add_user_and_mitigate_answers(p_username character varying, p_sex character)
  RETURNS character varying AS
$BODY$
DECLARE
	v_user_id integer;
BEGIN
	IF EXISTS (select 1 from aoc.art_user where username = p_username) THEN
		RETURN '-1';
	END IF;

	insert into aoc.art_user (username, sex) values (p_username, p_sex);

	select user_id into v_user_id from aoc.art_user where username = p_username;

	insert into aoc.art_answer(art_id, user_id, user_answer)
		select aa.art_id, au.user_id, aoc.random_answer() 
		from aoc.art_user au, aoc.art_art aa 
		where random() < 0.5; -- every 2nd art is randomly chosen

	return 'OK';
END; 
$BODY$
  LANGUAGE plpgsql VOLATILE;








DO LANGUAGE plpgsql $$
DECLARE

BEGIN
	perform aoc.add_user_and_mitigate_answers('ania', 'F');
	perform aoc.add_user_and_mitigate_answers('kasia', 'F');
	perform aoc.add_user_and_mitigate_answers('gosia', 'F');
	perform aoc.add_user_and_mitigate_answers('beata', 'F');
	perform aoc.add_user_and_mitigate_answers('krysia', 'F');
	perform aoc.add_user_and_mitigate_answers('Hania', 'F');
	
	perform aoc.add_user_and_mitigate_answers('tytus', 'M');
	perform aoc.add_user_and_mitigate_answers('romek', 'M');
	perform aoc.add_user_and_mitigate_answers('atomek', 'M');
	perform aoc.add_user_and_mitigate_answers('bolek', 'M');
	perform aoc.add_user_and_mitigate_answers('zenek', 'M');
	perform aoc.add_user_and_mitigate_answers('jarek', 'M');
END$$;
