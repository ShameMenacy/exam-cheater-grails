package exam.cheater

class Question {
    static enum Status {
        CREATING,
        CREATED,
        DELETING,
        HIDDEN,
        READONLY,
    }
    String content
    String description
    String jaDescription
    String hint
    Status status
    Date dateCreated
    Date lastUpdated

    static hasMany = [ansers: Answer]

    static hasOne = [category: Category, exam: Exam]

    static constraints = {
        content blank: false, maxSize: 15000
        description blank: true, maxSize: 15000
        jaDescription blank: true, maxSize: 15000
        hint blank: true, maxSize: 15000
        status defaultValue: Status.CREATING
    }

    static mapping = {
        sort 'id'
        order 'desc'
        content sqlType: 'text'
        description sqlType: 'text'
        jaDescription sqlType: 'text'
        hint sqlType: 'text'
    }
}
