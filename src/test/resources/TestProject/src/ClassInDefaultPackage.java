// default package (CtPackage.TOP_LEVEL_PACKAGE_NAME in Spoon= unnamed package)



public class ClassInDefaultPackage {
    private java.lang.String test;

    public ClassInDefaultPackage(java.lang.String a) {
        this.test = a;
    }

    public void shouldBeRemovable() {
        shouldNotBeRemovable();
    }

    public void shouldNotBeRemovable() {
    }
}

