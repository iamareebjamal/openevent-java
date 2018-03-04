package api.openevent.annotations.contraints

import javax.validation.constraints.Pattern

@Pattern(regexp = "^(https?)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]")
annotation class Url