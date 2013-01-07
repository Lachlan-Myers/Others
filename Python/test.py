'''Test client for COSC1092/93 assignment 2.

Run this script in the same directory as the enrol module created for the
assignment.
'''


import os, shutil

import enrol

# create test data
if os.path.exists('test_data'):
    shutil.rmtree('test_data')
os.mkdir('test_data')
enrol.writelines(os.path.join('test_data', 'CLASSES'), [
    'bw101.1:bw101:Mon 9.30:2.5.10:Alice Chiswick',
    'bw101.2:bw101:Wed 14.30:2.6.1:Bob Turnham',
    'bw330A:bw330:Tue 15.30:23.5.32:Carlos Stamford'])
enrol.writelines(os.path.join('test_data', 'SUBJECTS'), [
    'bw101:Introductory Basketweaving 1',
    'bw110:Introductory Basketweaving 2',
    'bw330:Underwater Basketweaving'])
enrol.writelines(os.path.join('test_data', 'VENUES'),
    ['2.5.10:18', '2.5.11:18', '2.6.1:22', '23.5.32:50'])
enrol.writelines(os.path.join('test_data', 'bw101.1.roll'),
    ['1124395', '1125622', '1109202', '1136607'])
enrol.writelines(os.path.join('test_data', 'bw330A.roll'),
    ['1125622', '1136607'])

# now test - all assertions should pass
e = enrol.Enrol("test_data")
assert e.subjects() == ['bw101', 'bw110', 'bw330']
assert e.subjectName('bw110') == 'Introductory Basketweaving 2'
assert e.classes('bw101') == ['bw101.1', 'bw101.2']
info = e.classInfo('bw101.1')
assert info[:-1] == ('bw101', 'Mon 9.30', '2.5.10', 'Alice Chiswick')
info[-1].sort()
assert info[-1] == ['1109202', '1124395', '1125622', '1136607']
assert e.checkStudent('1124395','bw101') == 'bw101.1'
assert e.checkStudent('1125622') == ['bw101.1', 'bw330A']
assert e.enrol('1124395','bw101.2') == 1
info = e.classInfo('bw101.1')
assert info[:-1] == ('bw101', 'Mon 9.30', '2.5.10', 'Alice Chiswick')
info[-1].sort()
assert info[-1] == ['1109202', '1125622', '1136607']
assert e.classInfo('bw101.2') == ('bw101', 'Wed 14.30', '2.6.1', 'Bob Turnham', ['1124395'])

