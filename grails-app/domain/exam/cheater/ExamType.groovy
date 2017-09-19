package exam.cheater

class ExamType {
    String name
    String description
    Boolean status
    Date dateCreated
    Date lastUpdated

    static hasMany = [exams: Exam]

    static constraints = {
        name blank: false, maxSize: 256
        description blank: true, maxSize: 15000
        status defaultValue: Boolean.FALSE
    }

    static mapping = {
        sort 'id'
        order 'desc'
        description sqlType: 'text'
    }
}
