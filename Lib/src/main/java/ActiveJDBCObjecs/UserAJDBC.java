package ActiveJDBCObjecs;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.HasMany;
import org.javalite.activejdbc.annotations.Table;

@Table("users")
@HasMany(child = UsersFolders.class, foreignKeyName = "user_id")
public class UserAJDBC extends Model {
    UserAJDBC(){}

    public Integer getUserId(){
        return this.getInteger("id");
    }
}
