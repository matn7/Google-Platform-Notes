class WasRun(TestCase):
    def __init__(self, name):
        self.wasRun = None
        TestCase.__init__(self, name)

    def testMethod(self):
        self.log = self.log + "testMethod "

    def tearDown(self):
        self.log = self.log + "tearDown "

    def testBrokenMethod(self):
        raise Exception
    
    def run(self):
        method = getattr(self, self.name)
        method()

    def setUp(self):
        self.log = "setUp "

test = WasRun("testMethod")
print(test.wasRun)
test.run()
print(test.wasRun)