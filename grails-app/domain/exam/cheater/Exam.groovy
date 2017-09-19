package exam.cheater

class Exam {
    String name
    String description
    Integer year
    Boolean status
    Date dateCreated
    Date lastUpdated

    static hasMany = [questions: Question]

    static hasOne = [examType: ExamType]

    static constraints = {
        name blank: false, maxSize: 256
        description blank: true, maxSize: 15000
        year blank: true, max: 9999
        status defaultValue: Boolean.FALSE
    }

    static mapping = {
        sort 'id'
        order 'desc'
        description sqlType: 'text'
    }
}
