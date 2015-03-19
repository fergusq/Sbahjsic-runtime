package sbahjsic.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	TestLexer.class,
	TestToken.class,
})
public class StandardTestSuite {}