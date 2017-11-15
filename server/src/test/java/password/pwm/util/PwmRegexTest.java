package password.pwm.util;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class PwmRegexTest {
    
    @Test
    public void testUsernameRegex() throws Exception {
        String regex = ".*?(@iubh\\.de|iubh-ds\\.de)$";
        
        String username = "wurst@iubh.de";
        assertThat(username.matches(regex)).isTrue();
        
        String username2 = "egal@iubh-ds.de";
        assertThat(username2.matches(regex)).isTrue();
        
        String username3 = "egal@egal.de";
        assertThat(username3.matches(regex)).isFalse();
        
        String username4 = "egal";
        assertThat(username4.matches(regex)).isFalse();

    }
    
    @Test
    public void testPhoneRegex() throws Exception {
        //Does not work
        //String regex1 = "^(((((((00|\\+)49[ \\-/]?)|0)[1-9][0-9]{1,4})[ \\-/]?)|((((00|\\+)49\\()|\\(0)[1-9][0-9]{1,4}\\)[ \\-/]?))[0-9]{1,7}([ \\-/]?[0-9]{1,5})?)$ ";
        String regex = "^\\+?[0-9. ()-/]{5,35}$";
        
        String p1 = "+ 49(0)89 365468";
        assertThat(p1.matches(regex)).isTrue();
        
        String p2 = "017890233333";
        assertThat(p2.matches(regex)).isTrue();
        
        String p3 = "017890233333d";
        assertThat(p3.matches(regex)).isFalse();
        
        String username4 = "egal";
        assertThat(username4.matches(regex)).isFalse();

    }

}
