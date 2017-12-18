DROP TRIGGER projectAssignmentVerif ON assignments;

DROP FUNCTION verify_Assignments();

CREATE OR REPLACE FUNCTION verify_Assignments() RETURNS TRIGGER AS $verify_Assignments$
DECLARE
    studentsNoAssignment int = 0;
    projectNoLimit int = 0;
BEGIN
    SELECT COUNT(*) FROM ASSIGNMENTS WHERE project_id = NEW.project_id INTO studentsNoAssignment;
    SELECT capacity FROM PROJECTS WHERE id = NEW.project_id INTO projectNoLimit;

    raise notice 'Value: %',studentsNoAssignment ;
    raise notice 'Value: %',projectNoLimit ;

    IF (studentsNoAssignment = projectNoLimit AND projectNoLimit != 0) THEN
      RAISE EXCEPTION '% Project Assignment Limit Exceded',capacity;
    END IF;
    RETURN NEW;
END;
$verify_Assignments$ LANGUAGE plpgsql;

CREATE TRIGGER projectAssignmentVerif BEFORE INSERT ON ASSIGNMENTS FOR EACH ROW EXECUTE PROCEDURE verify_Assignments();

INSERT INTO assignments VALUES(111,49);
