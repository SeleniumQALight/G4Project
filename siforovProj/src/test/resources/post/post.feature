Feature: Post feature

  Background:
    Given User opens 'Home' page

  @R004
  @BeforeDeletingAllPostsForDefaultUser
  @AfterDeletingAllPostsForDefaultUser
  Scenario: R004 Check numbers of posts
    And Create 2 new posts via API for 'default' user and 'default' password
    When User clicks on 'Profile' button in the 'Home' page
    Then User is redirected to Profile Page
    And User sees 2 posts in Posts list in the 'Profile' page
