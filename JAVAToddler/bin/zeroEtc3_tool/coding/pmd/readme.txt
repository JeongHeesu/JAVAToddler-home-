PMD(Programming Mistake Detector : 소스코드분석기)

service page : http://pmd.sf.net/

1. eclipse 설치
   eclipse plug-in :
   
        Help -> install New Software -> 
            name : pmd
            location : http://sourceforge.net/projects/pmd/files/pmd-eclipse/update-site/
        -> 본인의 이클립스 버젼에 맞는 버젼 선택 후 설치
        
2. Window -> Preferences -> PMD 확인

3. PMD 체크 대상 [프로젝트|팩키지|파일|에디터로 열린 파일] 선택 -> PMD -> Check Code With PMD 실행

4. Violatons Overview 뷰 와  Violations Outline 뷰를 참조.
   * Violatons Overview 뷰에 출력된 결과 더블클릭시 해당 파일이 에디터로 출력되며, Violations Outline 뷰에
       체크 내용과 코드라인이 출력됨
   * 체크 결과는 Error High > Error > Warning High -> Warning 으로 구분되어
     Violatons Overview 뷰 와  Violations Outline 뷰에 출력됨.
   * Violations Overview에서 Clear PMD Violations를 통해 삭제 가능
   * 체크 내용 수정 후 다시 PMD 실행으로 대응
   
** PMD로인해 한글 깨짐 현상 : eclipse.ini 
                      -Dfile.encoding=UTF-8 추가
                                        
                
                

