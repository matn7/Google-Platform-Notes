class TestCase:
    def __init__(self, name):
        self.name = name
    
    def setUp(self):
        pass

    def run(self):
        result = TestResult()
        result.testStarted()
        self.setUp()
        try:
            method = getattr(self, self.name)
            method()
        except:
            result.testFailed()
        self.tearDown()
    
    def testFailedResult(self):
        test = WasRun("testBrokenMethod")
        result = test.run()
        assert("1 run, 1 failed", result.summary)

    def tearDown(self):
        pass

suite = TestSuite()
suite.add(TestCaseTest("testTemplateMethod"))
suite.add(TestCaseTest("testResult"))
suite.add(TestCaseTest("testFailedResultFormatting"))
suite.add(TestCaseTest("tstFailedResult"))
suite.add(TestCaseTest("testSuite"))
tesult = TestResult()
suite.run(result)
print(result.summary)